package net.whale.UtilityPearls.compatibility;

import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class Table<R, C, V> {
    public static <R, C, V> Table<R, C, V> identityHashBasedTable() {
        return new Table<>(new IdentityHashMap<>(), IdentityHashMap::new);
    }

    private final Map<R, Map<C, V>> table;
    private final Function<R, Map<C, V>> rowMappingFunction;

    public Table(Map<R, Map<C, V>> table, Supplier<Map<C, V>> rowSupplier) {
        this.table = table;
        this.rowMappingFunction = (k -> rowSupplier.get());
    }

    @Nullable
    public V get(R row, C col) {
        Map<C, V> rowMap = getRow(row);
        return rowMap.get(col);
    }

    @Nullable
    public V put(R row, C col, V val) {
        Map<C, V> rowMap = getRow(row);
        return rowMap.put(col, val);
    }

    public Map<C, V> getRow(R row) {
        return table.computeIfAbsent(row, rowMappingFunction);
    }

    public boolean contains(R row, C col) {
        Map<C, V> map = table.get(row);
        return map != null && map.containsKey(col);
    }
}
