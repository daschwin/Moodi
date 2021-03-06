/**
 * Moodi
 * This is the moodi backend service
 * <p>
 * OpenAPI spec version: 1.0.0
 * Contact: luhansen@htwg-konstanz.de
 * <p>
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.moodi.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("code")
    private Integer code = null;
    @SerializedName("type")
    private String type = null;
    @SerializedName("message")
    private String message = null;

    /**
     **/
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     **/
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     **/
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiResponse apiResponse = (ApiResponse) o;
        return (this.code == null ? apiResponse.code == null : this.code.equals(apiResponse.code)) &&
                (this.type == null ? apiResponse.type == null : this.type.equals(apiResponse.type)) &&
                (this.message == null ? apiResponse.message == null : this.message.equals(apiResponse.message));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.code == null ? 0 : this.code.hashCode());
        result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
        result = 31 * result + (this.message == null ? 0 : this.message.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApiResponse {\n");

        sb.append("  code: ").append(code).append("\n");
        sb.append("  type: ").append(type).append("\n");
        sb.append("  message: ").append(message).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
