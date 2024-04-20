package com.example.error_correction.entity.response;

public class LlmCorrectionRes {
    private Output output;

    @Override
    public String toString() {
        return "LlmCorrectionRes{" +
                "output=" + output +
                '}';
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public static class  Output{
        private String inputText;
        private String correctedText;

        public String getInput_text() {
            return inputText;
        }

        @Override
        public String toString() {
            return "Output{" +
                    "input_text='" + inputText + '\'' +
                    ", corrected_text='" + correctedText + '\'' +
                    '}';
        }

        public void setInput_text(String input_text) {
            this.inputText = input_text;
        }

        public String getCorrected_text() {
            return correctedText;
        }

        public void setCorrected_text(String corrected_text) {
            this.correctedText = corrected_text;
        }
    }
}
