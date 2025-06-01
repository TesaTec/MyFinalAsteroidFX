package dk.sdu.cbse.main;

import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IHUDPluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.ModuleLayer;
import java.lang.module.ModuleFinder;
import java.nio.file.Paths;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;


@Configuration
class ModuleConfig {

    public ModuleConfig() {
    }

    @Bean
    public Game game() {
        return new Game();
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return loadServices(IGamePluginService.class);
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServices() {
        return loadServices(IEntityProcessingService.class);
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return loadServices(IPostEntityProcessingService.class);
    }

    @Bean
    public List<IHUDPluginService> hudPluginServices() {
        return loadServices(IHUDPluginService.class);
    }

    private <T> List<T> loadServices(Class<T> serviceClass) {
        ServiceLoader<T> loader = ServiceLoader.load(getLayer(),serviceClass);
        return StreamSupport.stream(loader.spliterator(), false)
                .collect(toList());
    }

   private ModuleLayer getLayer() {
        ModuleFinder finder = ModuleFinder.of(Paths.get("split-packages"));
        ModuleLayer parent = ModuleLayer.boot();
        List<String> modules = finder.findAll().stream().map(n -> n.descriptor().name()).collect(toList());
        java.lang.module.Configuration config = parent.configuration().resolve(finder, ModuleFinder.of(), modules);
        ModuleLayer layer = parent.defineModulesWithOneLoader(config, ClassLoader.getPlatformClassLoader());
        return layer;
   }

}




