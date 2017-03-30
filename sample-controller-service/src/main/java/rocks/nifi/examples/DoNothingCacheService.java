package rocks.nifi.examples;

import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.annotation.lifecycle.OnEnabled;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.controller.AbstractControllerService;
import org.apache.nifi.controller.ConfigurationContext;
import org.apache.nifi.distributed.cache.client.Deserializer;
import org.apache.nifi.distributed.cache.client.DistributedMapCacheClient;
import org.apache.nifi.distributed.cache.client.Serializer;
import org.apache.nifi.reporting.InitializationException;
import org.apache.nifi.util.file.monitor.SynchronousFileWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by http://www.nifi.rocks on 2/5/16.
 */

@Tags({"nifirocks", "properties"})
@CapabilityDescription("Provides a controller service to manage property files.")
public class DoNothingCacheService extends AbstractControllerService implements DistributedMapCacheClient {

    private static final Logger log = LoggerFactory.getLogger(DoNothingCacheService.class);

    private static final List<PropertyDescriptor> serviceProperties;

    static{
        final List<PropertyDescriptor> props = new ArrayList<>();
        serviceProperties = Collections.unmodifiableList(props);
    }

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ScheduledExecutorService executor;
    private Properties properties = new Properties();
    private SynchronousFileWatcher fileWatcher;

    private String configUri;
    private long reloadIntervalMilli;

    @Override
    protected List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return serviceProperties;
    }

    @OnEnabled
    public void onConfigured(final ConfigurationContext context) throws InitializationException{
        log.info("Starting do-nothing cache service");
    }


    @Override
    public <K, V> boolean putIfAbsent(K k, V v, Serializer<K> serializer, Serializer<V> serializer1) throws IOException {
        log.info("do-nothing putIfAbset called");
        return false;
    }

    @Override
    public <K, V> V getAndPutIfAbsent(K k, V v, Serializer<K> serializer, Serializer<V> serializer1, Deserializer<V> deserializer) throws IOException {
        log.info("do-nothing getAndPutIfAbset called");
        return null;
    }

    @Override
    public <K> boolean containsKey(K k, Serializer<K> serializer) throws IOException {
        log.info("do-nothing containsKey called");
        return false;
    }

    @Override
    public <K, V> void put(K k, V v, Serializer<K> serializer, Serializer<V> serializer1) throws IOException {
        log.info("do-nothing put called");
    }

    @Override
    public <K, V> V get(K k, Serializer<K> serializer, Deserializer<V> deserializer) throws IOException {
        log.info("do-nothing get called");
        return null;
    }

    @Override
    public void close() throws IOException {
        log.info("do-nothing close called");
    }

    @Override
    public <K> boolean remove(K k, Serializer<K> serializer) throws IOException {
        log.info("do-nothing remove called");
        return false;
    }

}
