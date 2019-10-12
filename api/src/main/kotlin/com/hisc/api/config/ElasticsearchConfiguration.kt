package com.hisc.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.inbot.eskotlinwrapper.IndexDAO
import io.inbot.eskotlinwrapper.JacksonModelReaderAndWriter
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.client.crudDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.reflect.KClass

@Configuration
class ElasticsearchConfiguration {

    @Bean(destroyMethod = "close")
    fun restHighLevelClient(): RestHighLevelClient {
        return RestHighLevelClient(host = "localhost", port = 9200)
    }
}

fun <T : Any> RestHighLevelClient.getDao(indexName: String, clazz: KClass<T>): IndexDAO<T> {
    return this.crudDao(indexName, refreshAllowed = true,
        modelReaderAndWriter = JacksonModelReaderAndWriter(
            clazz,
            ObjectMapper().findAndRegisterModules()
        ))
}
