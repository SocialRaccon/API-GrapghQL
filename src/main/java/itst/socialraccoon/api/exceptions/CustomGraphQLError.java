package itst.socialraccoon.api.exceptions;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class CustomGraphQLError implements GraphQLError {

    private final String message;
    private final List<SourceLocation> locations;

    public CustomGraphQLError(String message, List<SourceLocation> locations) {
        this.message = message;
        this.locations = locations;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return locations;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorClassification.errorClassification("CustomError");
    }

    @Override
    public Map<String, Object> toSpecification() {
        return Map.of("message", message, "locations", locations);
    }
}
