package com.smconsulting.backend.dto;

import java.util.List;

public record ServicesSectionResponse(
        String eyebrow,
        String titre,
        String intro,
        List<ServiceOfferingResponse> services
) {
}