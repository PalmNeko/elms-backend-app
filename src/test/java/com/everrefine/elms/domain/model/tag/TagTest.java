package com.everrefine.elms.domain.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TagTest {

  @Nested
  public class タグ作成 {

    @Test
    void createで新しいタグが作成される() {
      Tag tag = Tag.create("Java");
      assertNull(tag.id());
      assertEquals("Java", tag.name().value());
      assertNotNull(tag.createdAt());
      assertNotNull(tag.updatedAt());
    }
  }
}
