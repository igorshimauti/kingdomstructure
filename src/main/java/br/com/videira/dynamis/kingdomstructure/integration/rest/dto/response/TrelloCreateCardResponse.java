package br.com.videira.dynamis.kingdomstructure.integration.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TrelloCreateCardResponse(
        String id,
        String address,
        Badges badges,
        String cardRole,
        List<String> checkItemStates,
        Boolean closed,
        String coordinates,
        String creationMethod,
        String dateLastActivity,
        String desc,
        DescData descData,
        String due,
        String dueReminder,
        String idBoard,
        List<IdOnly> idChecklists,
        List<LabelRef> idLabels,
        String idList,
        List<String> idMembers,
        List<String> idMembersVoted,
        Integer idShort,
        List<String> labels,
        Limits limits,
        String locationName,
        Boolean manualCoverAttachment,
        String name,
        Long pos,
        String shortLink,
        String shortUrl,
        Boolean subscribed,
        String url,
        Cover cover
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Badges(
            AttachmentsByType attachmentsByType,
            Boolean location,
            Integer votes,
            Boolean viewingMemberVoted,
            Boolean subscribed,
            String fogbugz,
            Integer checkItems,
            Integer checkItemsChecked,
            Integer comments,
            Integer attachments,
            Boolean description,
            String due,
            String start,
            Boolean dueComplete
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record AttachmentsByType(
            TrelloAttachmentCounts trello
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TrelloAttachmentCounts(
            Integer board,
            Integer card
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DescData(
            Map<String, Object> emoji
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record IdOnly(
            String id
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record LabelRef(
            String id,
            String idBoard,
            String name,
            String color
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Limits(
            AttachmentLimits attachments
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record AttachmentLimits(
            PerBoardLimit perBoard
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PerBoardLimit(
            String status,
            Integer disableAt,
            Integer warnAt
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Cover(
            String color,
            Boolean idUploadedBackground,
            String size,
            String brightness,
            Boolean isTemplate
    ) {
    }
}
