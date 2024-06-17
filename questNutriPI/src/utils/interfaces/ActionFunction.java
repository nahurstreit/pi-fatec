package utils.interfaces;

public interface ActionFunction<T, P> {
	T execute(@SuppressWarnings("unchecked") P ...params);
}