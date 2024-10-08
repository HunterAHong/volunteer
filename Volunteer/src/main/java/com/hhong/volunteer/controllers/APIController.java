package com.hhong.Volunteer.controllers;

import com.google.gson.Gson;


public abstract class APIController {
    /**
     * Base path of API.
     */
    static final protected String BASE_PATH = "/api/v1/";

    /**
     * Used to serialize data and messages to JSON for transmitting through the REST API.
     */
    static final private Gson GSON = new Gson();

    /**
     * Turns the provided object into JSON, providing the class of the object to give a better
     * serialization. Use this if the type is known.
     *
     * @param obj The object to serialize to JSON
     * @param cls The class of the object
     *
     * @return The resulting JSON String
     */
    static final protected String toJson(final Object obj, final Class<JSONResponse> cls) {
        return GSON.toJson(obj, cls);
    }

    /**
     * Creates a JSONResponse for sending an error or informational message back to the user.
     *
     * @param status  The status of the request to send
     * @param message The detailed message to send
     *
     * @return The resulting JSON String
     */
    static final protected String responseMessage(final String status, final String message) {
        return toJson(new JSONResponse(status, message), JSONResponse.class);
    }

    /**
     * Creates a JSONResponse, setting the status to failed and the message to what is provided.
     *
     * @param message The detailed message to send
     *
     * @return The resulting JSON String
     */
    static final protected String errorResponse(final String message) {
        return responseMessage("failed", message);
    }

    /**
     * Creates a JSONResponse, setting the status to success and the message to what is provided.
     *
     * @param message The detailed message to send
     *
     * @return The resulting JSON String
     */
    static final protected String successResponse(final String message) {
        return responseMessage("success", message);
    }

    /**
     * Small class used for creating simple success/error messages to return via the REST API.
     * Contains a status of the action and an message.
     *
     * @author Kai Presler-Marshall
     */
    static protected class JSONResponse {

        /**
         * Status of the response (success/failed).
         */
        String status;

        /**
         * Message (what went wrong, something informational, etc).
         */
        String message;

        /**
         * Default constructor for JSONResponse.
         *
         * @param status  The status (success/failed)
         * @param message The message; any informational message desired
         */
        public JSONResponse(final String status, final String message) {
            this.status = status;
            this.message = message;
        }
    }
}
