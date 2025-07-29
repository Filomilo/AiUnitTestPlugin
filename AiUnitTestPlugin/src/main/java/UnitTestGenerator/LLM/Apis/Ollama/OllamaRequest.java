package UnitTestGenerator.LLM.Apis.Ollama;

import kotlinx.serialization.Serializable;

import java.util.List;

@Serializable
public class OllamaRequest {

    /**
     * (required) the model name
     */
    String model;

    /**
     * the prompt to generate a response for
     */
    String prompt;

    /**
     * the text after the model response
     */
    String suffix;

    /**
     * (optional) a list of base64-encoded images (for multimodal models such as llava)
     */
    List<String> images;

    /**
     * (for thinking models) should the model think before responding?
     */
    Boolean think;

    ////////////////////////////////////////////////// ADVANCED


    /**
     * the format to return a response in. Format can be json or a JSON schema
     */
    Object format;

    /**
     * additional model parameters listed in the documentation for the Modelfile such as temperature
     */
    Object options;

    /**
     * system message to (overrides what is defined in the Modelfile)
     */
    Object system;

    /**
     * the prompt template to use (overrides what is defined in the Modelfile)
     */
    Object template;

    /**
     * if false the response will be returned as a single response object, rather than a stream of objects
     */
    Boolean stream;

    /**
     * if true no formatting will be applied to the prompt. You may choose to use the raw parameter if you are specifying a full templated prompt in your request to the API
     */
    Boolean raw;

    /**
     * controls how long the model will stay loaded into memory following the request (default: 5m)
     */
    Boolean keep_alive;


}
