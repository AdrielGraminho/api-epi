package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.*;
import com.splenda.epi.entities.dtos.ExitItemDTO;
import com.splenda.epi.entities.dtos.InputExitDTO;
import com.splenda.epi.repository.ExitRepository;
import com.splenda.epi.services.EmployeeService;
import com.splenda.epi.services.ItemService;
import com.splenda.epi.services.PublicBusinessUnitService;
import com.splenda.epi.services.UserService;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExitServiceImplTest {

    @InjectMocks
    private ExitServiceImpl exitService;

    @Mock
    private ExitRepository exitRepository;

    @Mock
    private PublicBusinessUnitService publicBusinessUnitService;

    @Mock
    private ItemService itemService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private UserService userService;

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

    private ExitItemDTO exitItemDto;

    @Before
    public void setUp(){
        exitItemDto = new ExitItemDTO() {
            @Override
            public Integer getIdExit() {
                return 1;
            }

            @Override
            public String getItem() {
                return "Test";
            }

            @Override
            public String getBusinessUnit() {
                return "HONDA";
            }
        };
    }

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
    @Test
    public void shouldReturnAllExitByBusinessUnit(){
        LocalDate date = LocalDate.now();

        when(publicBusinessUnitService.findPublicBusinessUnitById(anyLong())).thenReturn(new PublicBusinessUnit());
        when(exitRepository.findAllDateNotReceivedByBusinessUnit(anyLong())).thenReturn(List.of(date));

        List<LocalDate> result = exitService.findAllDateNotReceivedByBusinessUnit(1);

        assertEquals(date, result.get(0));
        verify(exitRepository, times(1)).findAllDateNotReceivedByBusinessUnit(anyLong());
        verify(publicBusinessUnitService, times(1)).findPublicBusinessUnitById(anyLong());
    }

    @Test
    public void shouldReturnExitItemDtoWhenFindItemsByDateAndBusinessUnit(){
        List<ExitItemDTO> exitItemDtoList = new ArrayList<>();
        exitItemDtoList.add(exitItemDto);

        when(exitRepository.findExitItemByBusinessUnitAndDateForecast(any(), any())).thenReturn(exitItemDtoList);
        when(publicBusinessUnitService.findPublicBusinessUnitById(anyLong())).thenReturn(new PublicBusinessUnit());

        List<ExitItemDTO> result = exitService.findExitItemByBusinessUnitAndDateForecast(1, LocalDate.now());

        assertEquals(exitItemDtoList, result);
        verify(exitRepository, times(1)).findExitItemByBusinessUnitAndDateForecast(any(), any());
        verify(publicBusinessUnitService, times(1)).findPublicBusinessUnitById(anyLong());
    }

    @Test
    public void shouldSaveExit(){
        InputExitDTO inputExitDTO = InputExitDTO.builder()
                .idItem(1)
                .idEmployee(1)
                .build();

        when(itemService.findById(anyLong())).thenReturn(Item.builder().durability(150).build());
        when(employeeService.findById(anyLong())).thenReturn(new Employee());
        when(exitRepository.save(any())).thenReturn(new Exit());
        when(userService.findByUserName(any())).thenReturn(new User());
        when(auth.getPrincipal()).thenReturn(userDetailsTotal);

        exitService.save(inputExitDTO);

        verify(itemService, times(1)).findById(anyLong());
        verify(employeeService, times(1)).findById(anyLong());
        verify(exitRepository, times(1)).save(any());
        verify(userService, times(1)).findByUserName(any());
    }


}
