package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore mockTS1;
  private TorpedoStore mockTS2;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    // Create mock for the dependency TorpedoStore
    mockTS1 = mock(TorpedoStore.class);
    mockTS2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTS1, mockTS2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    // Verifying the mock: TS1 fire was called once, TS2 fire was not called
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    // Verifying the mock: TS1 fire was called once, TS2 fire was not called
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Both_Empty(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    // Verifying the mock
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_First_Empty(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.getTorpedoCount()).thenReturn(10);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    // Verifying the mock
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Second_Empty(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS1.getTorpedoCount()).thenReturn(10);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    // Verifying the mock
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_ALL_Both_Empty(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    // Verifying the mock
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_ALL_None_Empty(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS1.getTorpedoCount()).thenReturn(10);
    when(mockTS2.getTorpedoCount()).thenReturn(10);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    // Verifying the mock
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_ALL_None_Empty_Success(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS1.getTorpedoCount()).thenReturn(10);
    when(mockTS2.getTorpedoCount()).thenReturn(10);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    // Verifying the mock
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_ALL_Fire_Laser(){
    // Arrange
    // Set the behavior of the mock

    // Act
    boolean result = ship.fireLaser(FiringMode.ALL);

    // Assert
    // Verifying the mock

    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_PrimaryFiredLast(){
    // Arrange
    // Set the behavior of the mock
    this.ship.SetWasPrimaryFiredLast(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    // Verifying the mock
    verify(mockTS2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_PrimaryFiredLast_BUT_Second_is_Empty(){
    // Arrange
    // Set the behavior of the mock
    this.ship.SetWasPrimaryFiredLast(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    // Verifying the mock
    verify(mockTS1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_PrimaryFiredLast_Both_Empty(){
    // Arrange
    // Set the behavior of the mock
    this.ship.SetWasPrimaryFiredLast(true);
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    // Verifying the mock
    verify(mockTS1, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_ALL_First_Empty_First_NOT_Fires(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    // Verifying the mock
    verify(mockTS1, times(0)).fire(1);
  }


}
