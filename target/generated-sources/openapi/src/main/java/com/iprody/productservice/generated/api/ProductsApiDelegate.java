package com.iprody.productservice.generated.api;

import com.iprody.productservice.generated.model.ErrorResponse;
import com.iprody.productservice.generated.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link ProductsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-04-27T23:14:36.864308200+04:00[Asia/Yerevan]", comments = "Generator version: 7.9.0")
public interface ProductsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /products : Create a product
     *
     * @param product  (required)
     * @return Product successfully created (status code 201)
     *         or Bad request (status code 400)
     *         or Internal server error (status code 500)
     * @see ProductsApi#createProduct
     */
    default ResponseEntity<Product> createProduct(Product product) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"price\" : 6.0274563, \"name\" : \"name\", \"discount\" : 1.4658129, \"active\" : true, \"currency\" : \"currency\", \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"resourceGuid\" : \"resourceGuid\", \"details\" : [ \"details\", \"details\" ], \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"resourceGuid\" : \"resourceGuid\", \"details\" : [ \"details\", \"details\" ], \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /products : Deactivate product by id
     *
     * @param id The name of the product to deactivate (required)
     * @return Deactivate product (status code 200)
     *         or Product not found (status code 404)
     *         or Internal server error (status code 500)
     * @see ProductsApi#deactivateProduct
     */
    default ResponseEntity<List<Product>> deactivateProduct(Integer id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"price\" : 6.0274563, \"name\" : \"name\", \"discount\" : 1.4658129, \"active\" : true, \"currency\" : \"currency\", \"id\" : 0 }, { \"price\" : 6.0274563, \"name\" : \"name\", \"discount\" : 1.4658129, \"active\" : true, \"currency\" : \"currency\", \"id\" : 0 } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"resourceGuid\" : \"resourceGuid\", \"details\" : [ \"details\", \"details\" ], \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"resourceGuid\" : \"resourceGuid\", \"details\" : [ \"details\", \"details\" ], \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /products : Find all products
     *
     * @param limit  (optional, default to 25)
     * @param offset  (optional, default to 0)
     * @return List of products (status code 200)
     *         or Internal server error (status code 500)
     * @see ProductsApi#getAllProducts
     */
    default ResponseEntity<List<Product>> getAllProducts(Integer limit,
        Integer offset) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"price\" : 6.0274563, \"name\" : \"name\", \"discount\" : 1.4658129, \"active\" : true, \"currency\" : \"currency\", \"id\" : 0 }, { \"price\" : 6.0274563, \"name\" : \"name\", \"discount\" : 1.4658129, \"active\" : true, \"currency\" : \"currency\", \"id\" : 0 } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"resourceGuid\" : \"resourceGuid\", \"details\" : [ \"details\", \"details\" ], \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /products/{id} : Find a product by ID
     *
     * @param id  (required)
     * @return Product found (status code 200)
     *         or Product not found (status code 404)
     *         or Internal server error (status code 500)
     * @see ProductsApi#getProductById
     */
    default ResponseEntity<Product> getProductById(Integer id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"price\" : 6.0274563, \"name\" : \"name\", \"discount\" : 1.4658129, \"active\" : true, \"currency\" : \"currency\", \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"resourceGuid\" : \"resourceGuid\", \"details\" : [ \"details\", \"details\" ], \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"resourceGuid\" : \"resourceGuid\", \"details\" : [ \"details\", \"details\" ], \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
