package itst.socialraccoon.api.configuration;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLScalarType sqlTimestamp() {
        return GraphQLScalarType.newScalar()
                .name("SqlTimestamp")
                .description("Custom scalar type for SQL Timestamp")
                .coercing(new Coercing<Timestamp, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        return ((Timestamp) dataFetcherResult).toInstant().toString();
                    }

                    @Override
                    public Timestamp parseValue(Object input) {
                        return Timestamp.from(java.time.Instant.parse(input.toString()));
                    }

                    @Override
                    public Timestamp parseLiteral(Object input) {
                        if (input instanceof StringValue) {
                            return Timestamp.from(java.time.Instant.parse(((StringValue) input).getValue()));
                        }
                        return null;
                    }
                })
                .build();
    }
}
