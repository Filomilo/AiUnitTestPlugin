package UnitTestGenerator.LLM.Apis.Ollama;

public class OllamaResponse {

    String model;
    java.time.Instant created_at;

    /**
     * empty if the response was streamed, if not streamed, this will contain the full response
     */
    String response;
    Boolean done;

    /**
     * an encoding of the conversation used in this response, this can be sent in the next request to keep a conversational memory
     */
    Integer[] context;

    /**
     * time spent generating the response
     */
    Long total_duration;

    /**
     * time spent in nanoseconds loading the model
     */
    Long load_duration;

    /**
     * number of tokens in the prompt
     */
    Integer prompt_eval_count;

    /**
     * time spent in nanoseconds evaluating the prompt
     */
    Long prompt_eval_duration;

    /**
     * number of tokens in the response
     */
    Integer eval_count;


    /**
     * time in nanoseconds spent generating the response
     */
    Long eval_duration;


}
