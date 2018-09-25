package com.syscxp.biz.core.utils.zstack;


import com.syscxp.biz.core.utils.zstack.function.ForEachFunction;
import com.syscxp.biz.core.utils.zstack.function.Function;
import com.syscxp.biz.core.utils.zstack.function.ListFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 */
public class CollectionUtils {
    private static final Logger logger = LoggerFactory.getLogger(CollectionUtils.class);


    public static <K, V> List<K> transformToList(Collection<V> from, ListFunction<K, V> func) {
        List<K> ret = new ArrayList<K>();
        for (V v : from) {
            List<K> k = func.call(v);
            if (k == null) {
                continue;
            }
            ret.addAll(k);
        }

        return ret;
    }

    public static <K, V> List<K> transformToList(Collection<V> from, Function<K, V> func) {
        List<K> ret = new ArrayList<K>();
        for (V v : from) {
            K k = func.call(v);
            if (k == null) {
                continue;
            }
            ret.add(k);
        }

        return ret;
    }

    public static <K, V> Set<K> transformToSet(Collection<V> from, Function<K, V> func) {
        Set<K> ret = new HashSet<K>();
        for (V v : from) {
            K k = func.call(v);
            if (k == null) {
                continue;
            }
            ret.add(k);
        }

        return ret;
    }

    public static <K, V> Set<K> transformToSet(Collection<V> from, ListFunction<K, V> func) {
        Set<K> ret = new HashSet<K>();
        for (V v : from) {
            List<K> k = func.call(v);
            if (k == null) {
                continue;
            }
            ret.addAll(k);
        }

        return ret;
    }

    public static <K, V> K find(Collection<V> from, Function<K, V> func) {
        for (V v : from) {
            K k = func.call(v);
            if (k != null) {
                return k;
            }
        }

        return null;
    }

    public static <K> void forEach(Collection<K> cols, ForEachFunction<K> func) {
        for (K c : cols) {
            func.run(c);
        }
    }

    public static <K> void safeForEach(Collection<K> cols, ForEachFunction<K> func) {
        for (K c : cols) {
            try {
                func.run(c);
            } catch (Throwable t) {
                logger.warn(String.format("unhandled exception happened"), t);
            }
        }
    }

    public static <K> List<K> removeDuplicateFromList(List<K> lst) {
        return new ArrayList<K>(new LinkedHashSet<K>(lst));
    }
}
