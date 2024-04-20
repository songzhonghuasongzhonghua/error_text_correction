package com.example.error_correction.entity.response;

import java.util.List;

public class EnglishToolRes {
    private List<Match> matches;

    @Override
    public String toString() {
        return "EnglishToolRes{" +
                "matches=" + matches +
                '}';
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public static class Match {
        private List<Replacement> replacements;

        public List<Replacement> getReplacements() {
            return replacements;
        }

        @Override
        public String toString() {
            return "Match{" +
                    "replacements=" + replacements +
                    '}';
        }

        public void setReplacements(List<Replacement> replacements) {
            this.replacements = replacements;
        }

        public static class Replacement {
            private String value;

            @Override
            public String toString() {
                return "Replacement{" +
                        "value='" + value + '\'' +
                        '}';
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

    }
}
