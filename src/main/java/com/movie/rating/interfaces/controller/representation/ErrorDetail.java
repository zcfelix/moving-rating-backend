package com.movie.rating.interfaces.controller.representation;

import com.movie.rating.domain.ErrorCode;

import java.time.Instant;
import java.util.Map;

public record ErrorDetail(ErrorCode code, String path, Instant timestamp, Map<String, Object> data) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ErrorCode code;
        private String path;
        private Instant timestamp;
        private Map<String, Object> data;

        public Builder code(ErrorCode code) {
            this.code = code;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder data(Map<String, Object> data) {
            this.data = data;
            return this;
        }

        public ErrorDetail build() {
            return new ErrorDetail(code, path, timestamp, data);
        }
    }
}