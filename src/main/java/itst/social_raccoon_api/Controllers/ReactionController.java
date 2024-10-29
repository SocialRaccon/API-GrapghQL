package itst.social_raccoon_api.Controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import itst.social_raccoon_api.Dto.ReactionDTO;
import itst.social_raccoon_api.Models.ReactionModel;
import itst.social_raccoon_api.Services.PostService;
import itst.social_raccoon_api.Services.ReactionService;
import itst.social_raccoon_api.Services.ReactionTypeService;
import itst.social_raccoon_api.Services.UserService;

@RestController
@RequestMapping("reactions")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Reactions", description = "Provides methods to manage reactions.")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get all reactions by post id paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found reactions"),
            @ApiResponse(responseCode = "404", description = "Reactions not found")
    })
    @GetMapping("/{postId}")
    public List<ReactionDTO> getReactionsByPostId(
            @PathVariable Integer postId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<ReactionModel> reactions = reactionService.getReactionsByPostId(postId, page, pageSize);
        if (reactions.isEmpty()) {
            throw new NoSuchElementException();
        }
        return reactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get reaction count by post id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reaction count retrieved"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @GetMapping("/count/{postId}")
    public Integer getReactionCount(@PathVariable Integer postId) {
        return reactionService.getReactionCountByPostId(postId);
    }

    @GetMapping("/countType/{postId}")
    @Operation(summary = "Get reaction count by post  id and reaction type")
    public Integer getReactionCount(
            @PathVariable Integer postId,
            @RequestParam(value = "reactionType") Integer reactionType) {
        return reactionService.getReactionCountByPostIdAndReactionTypeId(postId, reactionType);
    }

    @Operation(summary = "React to a post or update an existing reaction.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reaction updated"),
            @ApiResponse(responseCode = "404", description = "Post or user not found")
    })
    @PostMapping("/{postId}")
    public ResponseEntity<ReactionDTO> reactOrUpdate(
            @PathVariable Integer postId,
            @RequestParam Integer userId,
            @RequestParam Integer reactionTypeId) {
        ReactionModel reaction = reactionService.reactOrUpdate(postId, userId, reactionTypeId);
        return new ResponseEntity<>(convertToDTO(reaction), HttpStatus.OK);
    }

    @Operation(summary = "Delete a user's reaction to a post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reaction deleted"),
            @ApiResponse(responseCode = "404", description = "Reaction not found")
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<Map<String, Boolean>> deleteReaction(
            @PathVariable Integer postId,
            @RequestParam Integer userId) {
        boolean deleted = reactionService.deleteReaction(postId, userId);
        if (deleted) {
            return new ResponseEntity<>(Map.of("deleted", true), HttpStatus.OK);
        } else {
            throw new NoSuchElementException();
        }
    }

    private ReactionDTO convertToDTO(ReactionModel reaction) {
        return modelMapper.map(reaction, ReactionDTO.class);
    }
}
