package sebas.lab.testing;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Test
    void testUsuarioExiste() {
        // Crear mock del repositorio
        UserRepository mockRepo = mock(UserRepository.class);
        // Configurar comportamiento del mock
        when(mockRepo.findById("123")).thenReturn(new User("123", "Juan"));

        // Inyectar en el servicio
        UserService service = new UserService(mockRepo);

        // Ejecutar
        String nombre = service.getUserName("123");

        // Verificar resultados
        assertEquals("Juan", nombre);
        verify(mockRepo, times(1)).findById("123");
    }
    @Test
    void testUsuarioNoExiste() {
        UserRepository mockRepo = mock(UserRepository.class);
        when(mockRepo.findById("999")).thenReturn(null);
        UserService service = new UserService(mockRepo);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.getUserName("999");
        });
        assertEquals("User not found", ex.getMessage());
        verify(mockRepo, times(1)).findById("999");
    }
}
