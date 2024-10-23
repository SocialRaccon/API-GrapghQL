package itst.social_raccoon_api.Controllers;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import itst.social_raccoon_api.Dto.PostDTO;
import itst.social_raccoon_api.Models.PostModel;
import itst.social_raccoon_api.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("posts")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "posts", description = "Provide methods to manage posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{userId}")
    @Operation(summary = "Get posts by user ID",
            description = "Retrieves all posts associated with the specified user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved posts")
    @ApiResponse(responseCode = "404", description = "No posts found for the user")
    public ResponseEntity<List<PostDTO>> getPostsByUserId(@PathVariable Integer userId) {
        List<PostModel> posts = postService.findByUser(userId);
        if (posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<PostDTO> postDTOs = posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(postDTOs);
    }

    private PostDTO convertToDTO(PostModel post) {
        PostDTO dto = modelMapper.map(post, PostDTO.class);
        // Add any additional processing if needed
        return dto;
    }

    private PostModel convertToEntity(PostDTO dto) {
        return modelMapper.map(dto, PostModel.class);
    }

    @PostMapping
    @JsonManagedReference
    @Operation(summary = "Crear un nuevo post",
            description = "Crea un nuevo post con la información proporcionada")
    @ApiResponse(responseCode = "201", description = "Post creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos del post inválidos")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        PostModel postModel = convertToEntity(postDTO);
        PostModel savedPost = postService.save(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedPost));
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
