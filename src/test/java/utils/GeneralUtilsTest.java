package utils;

import egonzalez.opanga.assessment.errors.BracesException;
import egonzalez.opanga.assessment.utils.GeneralUtils;
import egonzalez.opanga.assessment.utils.ValidationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import utils.test_cases.GeneralUtils_Str2Int_TestCasesProvider;

import java.util.Stack;

public class GeneralUtilsTest {

    @ParameterizedTest
    @ArgumentsSource(GeneralUtils_Str2Int_TestCasesProvider.class)
    public void str2int_should_convertStringsToInts(String[] inputs, Integer[] expected) {
        // Act
        Integer[] result = GeneralUtils.str2int(inputs);

        // Assert
        Assertions.assertEquals(expected.length, result.length);
        for (int i = 0; i < result.length; i++) {
            Assertions.assertEquals(expected[i], result[i]);
        }
    }

    @Test
    public void handleBraces_should_throwError() {
        // Arrange
        Stack<String> mockedBraces = new Stack<>();

        // Act
        Executable act = () -> ValidationUtils.handleBraces(mockedBraces, "}");

        // Assert
        Assertions.assertThrows(BracesException.class, act);
    }
}
