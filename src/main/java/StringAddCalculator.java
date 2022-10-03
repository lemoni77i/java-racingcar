import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringAddCalculator {

    public int sum(String inputStr) {
        try {
            StringAddCalculatorInput input = new StringAddCalculatorInput(inputStr);

            validateNegativeOperandExists(input.operands());

            return input.operands().stream().mapToInt(Integer::intValue).sum();
        } catch (Exception ex) {
            throw new IllegalArgumentException("계산 가능한 입력이 아닙니다.");
        }
    }

    private void validateNegativeOperandExists(List<Integer> operands) {
        if (operands.stream().anyMatch(n -> n < 0)) {
            throw new IllegalArgumentException("음수는 허용하지 않습니다.");
        }
    }
}

class StringAddCalculatorInput {
    private static final String[] defaultDelimiters = new String[]{",", ":"};
    private static final Pattern pattern = Pattern.compile("(?://(?<delimiter>.)\\\\n)?(?<sequence>.*)");

    private String[] delimiters;
    private List<Integer> operands;

    public StringAddCalculatorInput(String str) {
        Matcher matcher = pattern.matcher(str);

        if (matcher.lookingAt()) {
            delimiters = customDelimiter(matcher).map(d -> new String[]{d})
                    .orElse(defaultDelimiters);

            operands = sequenceToIntList(sequence(matcher));
        }
    }

    public Boolean isEmpty() {
        return operands.isEmpty();
    }

    public List<Integer> operands() {
        return this.operands;
    }

    private Optional<String> customDelimiter(Matcher matcher) {
        try {
            String delimiter = matcher.group("delimiter");
            return Optional.of(delimiter);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    private List<Integer> sequenceToIntList(String sequence) {
        if (sequence.isEmpty()) {
            return new ArrayList<Integer>();
        }

        return Arrays.stream(sequence.split(delimiterRegex()))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private String sequence(Matcher matcher) {
        return matcher.group("sequence");
    }

    private String delimiterRegex() {
        return "(" + String.join("|", delimiters) + ")";
    }
}