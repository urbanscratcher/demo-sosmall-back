package com.joun.sosmall.dtoRequest;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PageSortDto {
  int offset = 0;
  int limit = 10;
  String sortBy;
  String sortDirection = "ASC";

  public PageSortDto(Map<String, Object> query) {
    if (query.get("sortBy") != null) {

      sortBy = query.get("sortBy").toString();

      if (sortBy.startsWith("-")) {
        sortDirection = "DESC";
        sortBy = sortBy.substring(1);
      }
    }

    if (query.get("limit") != null) {
      limit = Integer.valueOf(query.get("limit").toString());
    }

    if (query.get("offset") != null) {
      offset = Integer.valueOf(query.get("offset").toString());
    }
  }
}
