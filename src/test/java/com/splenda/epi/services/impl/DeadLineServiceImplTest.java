package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.entities.dtos.DeadLineDTO;
import com.splenda.epi.entities.dtos.ExitItemDTO;
import com.splenda.epi.entities.dtos.PpraAndPcmsoDTO;
import com.splenda.epi.entities.exceptions.BusinessUnitIdMustBeProvidedException;
import com.splenda.epi.services.ExitService;
import com.splenda.epi.services.PpraAndPcmsoService;
import com.splenda.epi.services.PublicBusinessUnitService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeadLineServiceImplTest {

    @InjectMocks
    DeadlineServiceImpl deadlineService;

    @Mock
    ExitService exitService;

    @Mock
    PpraAndPcmsoService ppraAndPcmsoService;

    @Mock
    PublicBusinessUnitService publicBusinessUnitService;


    @Mock
    private Authentication auth;

    @BeforeEach
    public void initSecurityContext() {
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterEach
    public void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    SecurityContext securityContext = new SecurityContextImpl();

    UserDetails userDetailsTotal = new UserDetails() {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return  List.of(new SimpleGrantedAuthority("TOTAL"));
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String getUsername() {
            return null;
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    };

    UserDetails userDetailsBu = new UserDetails() {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return  List.of(new SimpleGrantedAuthority("BU"));
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String getUsername() {
            return "teste";
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    };

    @Before
    void initContext(){
        securityContext = new SecurityContextImpl();
        SecurityContextHolder.setContext(securityContext);
    }

    ExitItemDTO exitItemDTO = new ExitItemDTO() {
        @Override
        public Integer getIdExit() {
            return 1;
        }

        @Override
        public String getItem() {
            return "test";
        }

        @Override
        public String getBusinessUnit() {
            return "HONDA";
        }
    };

    PpraAndPcmsoDTO ppraAndPcmsoDTO = new PpraAndPcmsoDTO() {
        @Override
        public Integer getIdPpraAndPcmso() {
            return 2;
        }

        @Override
        public String getDescription() {
            return "test";
        }

        @Override
        public String getBusinessUnit() {
            return "HONDA";
        }
    };

    @Test
    public void shouldCallFindAllDeadLineWhenFindByBusinessUnitNullAndHaveTotalPermission(){
        when(auth.getPrincipal()).thenReturn(userDetailsTotal);
        when(exitService.findExitItemDateForecast(any())).thenReturn(List.of(exitItemDTO));
        when(ppraAndPcmsoService.findByExpirationDate(any())).thenReturn(List.of(ppraAndPcmsoDTO));

        List<DeadLineDTO> result = deadlineService.findDeadLineByIdBusinessUnitAndDate(null, LocalDate.now());

        verify(exitService, times(1)).findExitItemDateForecast(any());
        verify(ppraAndPcmsoService, times(1)).findByExpirationDate(any());
        assertEquals(exitItemDTO.getIdExit(), result.get(0).getIdDeadLine());
        assertEquals(ppraAndPcmsoDTO.getIdPpraAndPcmso(), result.get(1).getIdDeadLine());

    }

    @Test
    public void shouldReturnExceptionWhenFindDeadlinesAndBusinessUnitIsNullAndDontHaveTotalPermission(){
        when(auth.getPrincipal()).thenReturn(userDetailsBu);

        String exceptionKey = "";
        try {
            deadlineService.findDeadLineByIdBusinessUnitAndDate(null, LocalDate.now());
        }catch (BusinessUnitIdMustBeProvidedException businessUnitIdMustBeProvidedException){
            exceptionKey = businessUnitIdMustBeProvidedException.getKey();
        }
        assertEquals("business-unit.id-must-be-provided", exceptionKey);
    }

    @Test
    public void shouldCallFindAllDeadLineWhenFindByBusinessUnitNotNull(){
        when(exitService.findExitItemByBusinessUnitAndDateForecast(any(), any())).thenReturn(List.of(exitItemDTO));
        when(ppraAndPcmsoService.findByBusinessUnitAndExpirationDate(any(), any())).thenReturn(List.of(ppraAndPcmsoDTO));
        when(publicBusinessUnitService.findPublicBusinessUnitById(Long.valueOf(anyLong()))).thenReturn(new PublicBusinessUnit());

        List<DeadLineDTO> result = deadlineService.findDeadLineByIdBusinessUnitAndDate(1, LocalDate.now());

        verify(exitService, times(1)).findExitItemByBusinessUnitAndDateForecast(any(), any());
        verify(ppraAndPcmsoService, times(1)).findByBusinessUnitAndExpirationDate(any(), any());
        assertEquals(exitItemDTO.getIdExit(), result.get(0).getIdDeadLine());
        assertEquals(ppraAndPcmsoDTO.getIdPpraAndPcmso(), result.get(1).getIdDeadLine());

    }
}
