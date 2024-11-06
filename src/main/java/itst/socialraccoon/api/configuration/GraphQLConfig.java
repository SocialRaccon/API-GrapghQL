package itst.socialraccoon.api.configuration;

import graphql.language.StringValue;
import graphql.schema.*;
import itst.socialraccoon.api.exceptions.GraphQLExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.sql.Timestamp;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLScalarType sqlTimestampScalar() {
        return GraphQLScalarType.newScalar()
                .name("SqlTimestamp")
                .description("Java SQL Timestamp scalar")
                .coercing(new Coercing<Timestamp, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        if (dataFetcherResult instanceof Timestamp) {
                            return dataFetcherResult.toString();
                        }
                        throw new CoercingSerializeException("Expected a Timestamp object.");
                    }

                    @Override
                    public Timestamp parseValue(Object input) {
                        try {
                            if (input instanceof String) {
                                return Timestamp.valueOf((String) input);
                            }
                            throw new CoercingParseValueException("Expected a String");
                        } catch (IllegalArgumentException e) {
                            throw new CoercingParseValueException(
                                    String.format("Not a valid timestamp: '%s'.", input), e
                            );
                        }
                    }

                    @Override
                    public Timestamp parseLiteral(Object input) {
                        if (input instanceof StringValue) {
                            try {
                                return Timestamp.valueOf(((StringValue) input).getValue());
                            } catch (IllegalArgumentException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        }
                        throw new CoercingParseLiteralException("Expected a StringValue.");
                    }
                })
                .build();
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(GraphQLScalarType sqlTimestampScalar) {
        return builder -> builder.scalar(sqlTimestampScalar);
    }

    @Bean public GraphQLExceptionHandler graphQLExceptionHandler() { return new GraphQLExceptionHandler(); }
}
