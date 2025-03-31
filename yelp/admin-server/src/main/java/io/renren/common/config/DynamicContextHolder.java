

package io.renren.common.config;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Multi-data source context
 *
 * @author Mark sunlightcs@gmail.com
 */
public class DynamicContextHolder {
    private static final ThreadLocal<Deque<String>> CONTEXT_HOLDER = ThreadLocal.withInitial(ArrayDeque::new);

    /**
     * Obtain the current thread data source
     *
     * @return Data source name
     */
    public static String peek() {
        return CONTEXT_HOLDER.get().peek();
    }

    /**
     * Set the current thread data source
     *
     * @param dataSource Data source name
     */
    public static void push(String dataSource) {
        CONTEXT_HOLDER.get().push(dataSource);
    }

    /**
     * Clear the current thread data source
     */
    public static void poll() {
        Deque<String> deque = CONTEXT_HOLDER.get();
        deque.poll();
        if (deque.isEmpty()) {
            CONTEXT_HOLDER.remove();
        }
    }

}