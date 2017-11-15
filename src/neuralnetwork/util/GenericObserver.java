package neuralnetwork.util;

/**
 *
 * @author artur
 * @param <T>
 */
public interface GenericObserver<T>
{
    public void notify( T source );
}
