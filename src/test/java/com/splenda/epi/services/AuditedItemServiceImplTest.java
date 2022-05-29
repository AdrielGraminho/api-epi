package com.splenda.epi.services;

import com.splenda.epi.dtos.InputAuditedItemDTO;
import com.splenda.epi.entities.core.AuditItem;
import com.splenda.epi.entities.core.AuditedItem;
import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.entities.core.User;
import com.splenda.epi.repository.AuditedItemRepository;
import com.splenda.epi.services.impl.AuditedItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditedItemServiceImplTest {
    @InjectMocks
    AuditedItemServiceImpl auditedItemService;

    @Mock
    AuditedItemRepository auditedItemRepository;

    @Mock
    AuditService auditService;

    @Mock
    UserService userService;

    @Mock
    PublicBusinessUnitService publicBusinessUnitService;

    InputAuditedItemDTO inputAuditedItemdto = new InputAuditedItemDTO();

    @BeforeEach
    public void setUp(){
        inputAuditedItemdto = InputAuditedItemDTO.builder()
                .itemId(1)
                .businessUnitId(1)
                .build();
    }

    private UserDetails getUserDetails(){
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return "123";
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
        return userDetails;
    }


    @Test
    public void shouldBeSaveAuditedItemWhenSaveNewAuditedItem(){
        when(userService.getUserDetailsLogged()).thenReturn(getUserDetails());
        when(userService.findByUserName(anyString())).thenReturn(new User());
        when(auditService.findAuditItemById(anyLong())).thenReturn(new AuditItem());
        when(publicBusinessUnitService.findPublicBusinessUnitById(anyLong())).thenReturn(new PublicBusinessUnit());
        when(auditedItemRepository.findByAuditItemBusinessUnitAndDate(anyLong(), anyLong())).thenReturn(Optional.empty());
        when(auditedItemRepository.save(any())).thenReturn(new AuditedItem());

        auditedItemService.save(inputAuditedItemdto);


        verify(userService, times(1)).getUserDetailsLogged();
        verify(userService, times(1)).findByUserName(anyString());
        verify(auditService, times(1)).findAuditItemById(anyLong());
        verify(publicBusinessUnitService, times(1)).findPublicBusinessUnitById(anyLong());
        verify(auditedItemRepository, times(1)).findByAuditItemBusinessUnitAndDate(anyLong(), anyLong());
        verify(auditedItemRepository, times(1)).save(any());
    }

    @Test
    public void shouldBeSaveAuditedItemWhenSaveAndThisExist(){
        when(userService.getUserDetailsLogged()).thenReturn(getUserDetails());
        when(userService.findByUserName(anyString())).thenReturn(new User());
        when(auditService.findAuditItemById(anyLong())).thenReturn(new AuditItem());
        when(publicBusinessUnitService.findPublicBusinessUnitById(anyLong())).thenReturn(new PublicBusinessUnit());
        when(auditedItemRepository.findByAuditItemBusinessUnitAndDate(anyLong(), anyLong())).thenReturn(Optional.of(AuditedItem.builder().idAuditedItem(1l).build()));
        when(auditedItemRepository.save(any())).thenReturn(new AuditedItem());

        auditedItemService.save(inputAuditedItemdto);

        verify(userService, times(1)).getUserDetailsLogged();
        verify(userService, times(1)).findByUserName(anyString());
        verify(auditService, times(1)).findAuditItemById(anyLong());
        verify(publicBusinessUnitService, times(1)).findPublicBusinessUnitById(anyLong());
        verify(auditedItemRepository, times(1)).findByAuditItemBusinessUnitAndDate(anyLong(), anyLong());
        verify(auditedItemRepository, times(1)).save(any());
    }
}
