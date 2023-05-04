package com.joun.sosmall.dtoResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResponseDto<T> {
  int totalCount;
  T list;
}
