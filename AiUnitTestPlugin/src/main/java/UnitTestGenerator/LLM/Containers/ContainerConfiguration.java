package UnitTestGenerator.LLM.Containers;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class ContainerConfiguration {
    private String image;
    @NonNull
    private Long ramBytes;
}
