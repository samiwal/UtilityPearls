package net.whale.UtilityPearls.compatibility;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;
import java.util.stream.Stream;

public class RegistryWrapper<T> {
    public static <T> RegistryWrapper<T> getRegistry(ResourceKey<? extends Registry<T>> key) {
        Registry<? extends Registry<?>> rootRegistry = BuiltInRegistries.REGISTRY;
        Registry<?> registry = rootRegistry.get(key.location());
        RegistryWrapper<?> registryWrapper = new RegistryWrapper<>(registry);
        @SuppressWarnings("unchecked")
        RegistryWrapper<T> castPlatformRegistry = (RegistryWrapper<T>) registryWrapper;
        return castPlatformRegistry;
    }

    private final Registry<T> registry;

    private RegistryWrapper(Registry<T> registry) {
        this.registry = registry;
    }

    public Stream<Holder.Reference<T>> getHolderStream() {
        return this.registry.holders();
    }
    public Optional<ResourceLocation> getRegistryName(T entry) {
        return this.registry.getResourceKey(entry)
                .map(ResourceKey::location);
    }
}
