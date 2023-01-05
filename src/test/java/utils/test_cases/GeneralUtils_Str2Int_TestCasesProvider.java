package utils.test_cases;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class GeneralUtils_Str2Int_TestCasesProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(
                        new String[]{"1234", "321", "432"},
                        new Integer[]{1234, 321, 432}),
                Arguments.of(
                        new String[]{"0", "99999999", "1"},
                        new Integer[]{0, 99999999, 1})
                );
    }
}
