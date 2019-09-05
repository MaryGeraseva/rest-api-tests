package rest.petStoreTests.PetTests.getTests;

import common.BaseTest;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.DataGenerator;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PetGetNegativeTests extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;

    @ParameterizedTest(name = "Pet endpoint GET positive test #{0}")
    @ValueSource(ints = {1, 2, 3})
    @Step("Pet endpoint GET positive test started ")
    @Description(value = "test checks GET request with nonexistent id, " +
            "expected response status code 404 and Pet not found message")
    public void PetGetNotFoundTest404(int testId) {
        controller = new PetController();
        assertions = new PetAssertions();

        String petId = DataGenerator.getRandomId();
        response = controller.deletePet(petId);

        response = controller.getResponseById(petId);
        assertions.assertResponseBodyAndStatus(response, StatusCodes.CODE404);
    }

    @ParameterizedTest(name = "Pet endpoint GET positive test #{0}")
    @MethodSource("rest.petStoreTests.PetTests.dataProviders.PetDataProvider#invalidId")
    @Step("Pet endpoint GET positive test started ")
    @Description(value = "test checks GET request with invalid id, " +
            "expected response status code 400 and Invalid ID supplied")
    public void PetGetInvalidId400(int testId, String id) {
        controller = new PetController();
        assertions = new PetAssertions();

        response = controller.getResponseById(id);

        assertions.assertResponseBodyAndStatus(response, StatusCodes.CODE400);
    }
}