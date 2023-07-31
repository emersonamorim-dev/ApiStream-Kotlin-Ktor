package com.ApiStream.repositories

import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*
import com.ApiStream.repositories.PodcastRepository
import com.ApiStream.model.Podcast
import com.ApiStream.model.Videocast

val dynamoDbClient: DynamoDbClient = DynamoDbClient.builder()
    .region(Region.US_WEST_2) // substitua pela sua região
    .build()

class DynamoDbPodcastRepository : PodcastRepository {
    override fun getAll(): List<Podcast> {
        val scanRequest = ScanRequest.builder()
            .tableName("podcasts")
            .build()

        val scanResponse = dynamoDbClient.scan(scanRequest)

        return scanResponse.items().map { 
            Podcast(
                it["id"]?.n()?.toInt() ?: 0,
                it["title"]?.s() ?: "",
                it["url"]?.s() ?: ""
            )
        }
    }

    override fun add(podcast: Podcast): Podcast {
        val putRequest = PutItemRequest.builder()
            .tableName("podcasts")
            .item(
                mapOf(
                    "id" to AttributeValue.builder().n(podcast.id.toString()).build(),
                    "title" to AttributeValue.builder().s(podcast.title).build(),
                    "url" to AttributeValue.builder().s(podcast.url).build()
                )
            )
            .build()

        dynamoDbClient.putItem(putRequest)

        return podcast
    }
    override fun findById(id: Int): Podcast? {
        val getRequest = GetItemRequest.builder()
            .tableName("podcasts")
            .key(mapOf("id" to AttributeValue.builder().n(id.toString()).build()))
            .build()

        val getResponse = dynamoDbClient.getItem(getRequest)

        return getResponse.item()?.let { 
            Podcast(
                it["id"]?.n()?.toInt() ?: 0,
                it["title"]?.s() ?: "",
                it["url"]?.s() ?: ""
            )
        }
    }

    override fun update(podcast: Podcast): Podcast {
        val updateRequest = UpdateItemRequest.builder()
            .tableName("podcasts")
            .key(mapOf("id" to AttributeValue.builder().n(podcast.id.toString()).build()))
            .attributeUpdates(mapOf(
                "title" to AttributeValueUpdate.builder().value(AttributeValue.builder().s(podcast.title).build()).build(),
                "url" to AttributeValueUpdate.builder().value(AttributeValue.builder().s(podcast.url).build()).build()
            ))
            .build()

        dynamoDbClient.updateItem(updateRequest)

        return podcast
    }

    override fun delete(id: Int) {
        val deleteRequest = DeleteItemRequest.builder()
            .tableName("podcasts")
            .key(mapOf("id" to AttributeValue.builder().n(id.toString()).build()))
            .build()

        dynamoDbClient.deleteItem(deleteRequest)
    }
}
