package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.entities.exceptions.BusinessUnitNotFoundException;
import com.splenda.epi.repository.PublicBusinessUnitRepository;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublicBusinessUnitServiceImplTest {
    @InjectMocks
    PublicBusinessUnitServiceImpl publicBusinessUnitService;

    @Mock
    PublicBusinessUnitRepository publicBusinessUnitRepository;

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
    PublicBusinessUnit publicBusinessUnit = new PublicBusinessUnit();

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
    @BeforeEach
    public void setUp(){
        publicBusinessUnit = PublicBusinessUnit.builder()
                .idBusinessUnit(1l)
                .code(10001)
                .codeMetadados("001")
                .build();
    }

    @Test
    public void shouldBeReturnExceptionWhenFindBusinessUnitByIdAndNotFound(){
        when(publicBusinessUnitRepository.findById(anyLong())).thenReturn(Optional.empty());

        String exceptionKey = "";
        try {
            publicBusinessUnitService.findPublicBusinessUnitById(1l);
        } catch (BusinessUnitNotFoundException businessUnitNotFoundException){
            exceptionKey = businessUnitNotFoundException.getKey();
        }

        assertEquals("business-unit.not-found", exceptionKey);
    }

    @Test
    public void shouldBeReturnPublicBusinessUnitWhenFindBusinessUnitById(){
        when(publicBusinessUnitRepository.findById(anyLong())).thenReturn(Optional.of(publicBusinessUnit));

        PublicBusinessUnit result = publicBusinessUnitService.findPublicBusinessUnitById(1l);

        assertEquals(publicBusinessUnit, result);
        verify(publicBusinessUnitRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldBeReturnExceptionWhenFindPublicBusinessUnitByIdAndNotFound(){
        when(publicBusinessUnitRepository.findById(anyLong())).thenReturn(Optional.empty());

        String keyException = "";
        try {
            publicBusinessUnitService.findPublicBusinessUnitById(1l);
        } catch (BusinessUnitNotFoundException businessUnitNotFoundException){
            keyException = businessUnitNotFoundException.getKey();
        }

        assertEquals("business-unit.not-found", keyException);
        verify(publicBusinessUnitRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldBeReturnPublicBusinessUnitWhenFindById(){
        PublicBusinessUnit publicBusinessUnit = PublicBusinessUnit.builder().idBusinessUnit(1l).build();
        when(publicBusinessUnitRepository.findById(anyLong())).thenReturn(Optional.of(publicBusinessUnit));

        PublicBusinessUnit result = publicBusinessUnitService.findPublicBusinessUnitById(1l);

        assertEquals(publicBusinessUnit, result);
        verify(publicBusinessUnitRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldBeReturnExceptionWhenFindBusinessUnitByAndHaveTotalPermissionAndNotFoundBu(){
        when(publicBusinessUnitRepository.findAll()).thenReturn(List.of());
        when(auth.getPrincipal()).thenReturn(userDetailsTotal);

        String keyException = "";
        try {
            publicBusinessUnitService.findByPermissionUser();
        } catch (BusinessUnitNotFoundException businessUnitNotFoundException){
            keyException = businessUnitNotFoundException.getKey();
        }

        assertEquals("business-unit.not-found", keyException);
        verify(publicBusinessUnitRepository, times(1)).findAll();
    }

    @Test
    public void shouldBeReturnAllBusinessUnitWhenFindBusinessUnitAndHaveTotalPermission(){
        PublicBusinessUnit publicBusinessUnit = PublicBusinessUnit.builder().idBusinessUnit(1l).build();

        when(publicBusinessUnitRepository.findAll()).thenReturn(List.of(publicBusinessUnit));
        when(auth.getPrincipal()).thenReturn(userDetailsTotal);

        List<PublicBusinessUnit> result = publicBusinessUnitService.findByPermissionUser();

        assertEquals(publicBusinessUnit, result.get(0));
        verify(publicBusinessUnitRepository, times(1)).findAll();
    }

    @Test
    public void shouldBeReturnExceptionWhenFindBusinessUnitByAndHaveBuPermissionAndNotFoundBu(){
        when(publicBusinessUnitRepository.findByUserName(anyString())).thenReturn(List.of());
        when(auth.getPrincipal()).thenReturn(userDetailsBu);

        String keyException = "";
        try {
            publicBusinessUnitService.findByPermissionUser();
        } catch (BusinessUnitNotFoundException businessUnitNotFoundException){
            keyException = businessUnitNotFoundException.getKey();
        }

        assertEquals("business-unit.not-found", keyException);
        verify(publicBusinessUnitRepository, times(1)).findByUserName(anyString());
    }

    @Test
    public void shouldBeReturnBusinessUnitByUserLogedWhenFindByBusinessUnitAndHaveBUPermission(){
        PublicBusinessUnit publicBusinessUnit = PublicBusinessUnit.builder().idBusinessUnit(1l).build();

        when(publicBusinessUnitRepository.findByUserName(anyString())).thenReturn(List.of(publicBusinessUnit));
        when(auth.getPrincipal()).thenReturn(userDetailsBu);

        List<PublicBusinessUnit> result = publicBusinessUnitService.findByPermissionUser();

        assertEquals(publicBusinessUnit, result.get(0));
        verify(publicBusinessUnitRepository, times(1)).findByUserName(anyString());
    }
}
