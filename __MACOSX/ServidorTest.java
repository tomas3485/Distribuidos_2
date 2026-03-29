import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.rmi.RemoteException;

// nuevo archivo solo para hacer pruebas

public class ServidorTest {

    // Creo una instancia calc , aunque se podria crear metodos nuevos
    private final Interfaz calc = new Interfaz() {
        
        public float sumar(float n1, float n2) throws RemoteException {
            return n1 + n2;
        }

        public float restar(float n1, float n2) throws RemoteException {
            return n1 - n2;
        }

        public float multiplicar(float n1, float n2) throws RemoteException {
            return n1 * n2;
        }

        public float dividir(float n1, float n2) throws RemoteException {
            if (n2 == 0) throw new ArithmeticException("Error: No se puede dividir por 0");
            return n1 / n2;
        }

        public float raizCuadrada(float numero) throws RemoteException {
            if (numero < 0) throw new ArithmeticException("Error: No se puede hacer raz de numeros negativos");
            return (float) Math.sqrt(numero);
        }
    };

    @Test
    void testSumar() throws RemoteException { assertEquals(5, calc.sumar(2, 3)); } //Uso assertEqual para comprobar si la suma de 2,3 es 5
                                                                                   //Se usa porque funciona con JUnits reports

    @Test
    void testRestar() throws RemoteException { assertEquals(1, calc.restar(3, 2)); }

    @Test
    void testMultiplicar() throws RemoteException { assertEquals(6, calc.multiplicar(2, 3)); }

    @Test
    void testDividir() throws RemoteException { assertEquals(2, calc.dividir(6, 3)); }

    @Test
    void testDividirPorCero() {
        Exception ex = assertThrows(ArithmeticException.class, () -> { try { calc.dividir(5, 0); } catch (RemoteException e) { throw new RuntimeException(e); }});
        assertEquals("ERROR: No se puede dividir por 0", ex.getMessage()); 
    }

    @Test
    void testRaizCuadrada() throws RemoteException { assertEquals(3, calc.raizCuadrada(9)); }

    @Test
    void testRaizCuadradaNegativa() {
        Exception ex = assertThrows(ArithmeticException.class, () -> {
            try { calc.raizCuadrada(-1); } catch (RemoteException e) { throw new RuntimeException(e); }
        });
        assertEquals("ERROR: No se puede hacer una raiz de numeros negativos", ex.getMessage());
    }
}