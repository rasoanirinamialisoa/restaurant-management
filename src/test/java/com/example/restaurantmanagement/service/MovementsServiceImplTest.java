package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Movements;
import com.example.restaurantmanagement.repository.MovementsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovementsServiceImplTest {

    @Mock
    private MovementsRepository movementsRepository;

    @InjectMocks
    private MovementsServiceImpl movementsService;

    private Movements testMovement;
    private Movements existingMovement;

    @BeforeEach
    public void setUp() {
        testMovement = new Movements();
        testMovement.setId(1);
        testMovement.setType("exit");
        testMovement.setQuantity(10.0);
        testMovement.setDate(Instant.now());
        testMovement.setQuantityRemaining(90.0);
        testMovement.setIdIngredientTemplate(1);

        existingMovement = new Movements();
        existingMovement.setId(1);
        existingMovement.setType("entrance");
        existingMovement.setQuantity(500.0);
        existingMovement.setDate(Instant.now());
        existingMovement.setQuantityRemaining(500.0);
        existingMovement.setIdIngredientTemplate(1);
    }

    @Test
    public void testAddStock() throws SQLException {
        when(movementsRepository.createMovement(any(Movements.class))).thenReturn(testMovement);

        Movements createdMovement = movementsService.addStock(testMovement);

        assertNotNull(createdMovement);
        assertEquals(testMovement.getType(), createdMovement.getType());
        assertEquals(testMovement.getQuantity(), createdMovement.getQuantity());
        verify(movementsRepository, times(1)).createMovement(any(Movements.class));
    }

    @Test
    public void testConsumeStock() throws SQLException {
        when(movementsRepository.getMovementById(testMovement.getId())).thenReturn(existingMovement);

        double expectedNewQuantity = existingMovement.getQuantity() - testMovement.getQuantity();
        existingMovement.setQuantity(expectedNewQuantity);

        when(movementsRepository.updateMovement(anyInt(), any(Movements.class))).thenReturn(existingMovement);

        Movements updatedMovement = movementsService.consumeStock(testMovement);

        assertNotNull(updatedMovement);
        assertEquals(expectedNewQuantity, updatedMovement.getQuantity());
        verify(movementsRepository, times(1)).getMovementById(testMovement.getId());
        verify(movementsRepository, times(1)).updateMovement(anyInt(), any(Movements.class));
    }

    @Test
    public void testConsumeStockNotFound() throws SQLException {
        when(movementsRepository.getMovementById(testMovement.getId())).thenReturn(null);

        Movements updatedMovement = movementsService.consumeStock(testMovement);

        assertNull(updatedMovement);
        verify(movementsRepository, times(1)).getMovementById(testMovement.getId());
        verify(movementsRepository, times(0)).updateMovement(anyInt(), any(Movements.class));
    }

}
