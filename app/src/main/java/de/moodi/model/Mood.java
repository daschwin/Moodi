package de.moodi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Mood
 */
public class Mood {
    @SerializedName("id")
    private String id = null;

    @SerializedName("value")
    @NotNull
    private Long value = null;

    @SerializedName("timestamp")
    @NotNull
    private Long timestamp = null;

    @SerializedName("userId")
    @Size(min = 1)
    @NotNull
    private String userId = null;

    @SerializedName("teamId")
    @Size(min = 1)
    @NotNull
    private String teamId = null;

    public Mood(long value, String userId, String teamId) {
        this.value = value;
        this.userId = userId;
        this.teamId = teamId;
        this.timestamp = System.currentTimeMillis();
    }

    public Mood id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    public String getId() {
        return id;
    }

    public Mood value(Long value) {
        this.value = value;
        return this;
    }

    /**
     * Get value
     *
     * @return value
     **/
    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Mood timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get timestamp
     *
     * @return timestamp
     **/
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Mood userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Get userId
     *
     * @return userId
     **/
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Mood teamId(String teamId) {
        this.teamId = teamId;
        return this;
    }

    /**
     * Get teamId
     *
     * @return teamId
     **/
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mood mood = (Mood) o;
        return Objects.equals(this.id, mood.id) &&
                Objects.equals(this.value, mood.value) &&
                Objects.equals(this.timestamp, mood.timestamp) &&
                Objects.equals(this.userId, mood.userId) &&
                Objects.equals(this.teamId, mood.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, timestamp, userId, teamId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Mood {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    teamId: ").append(toIndentedString(teamId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

