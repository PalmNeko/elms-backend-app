package com.everrefine.elms.domain.model.tag;

import com.everrefine.elms.domain.exception.InvalidValueException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** タグのコレクション */
public class TagCollection {

  // 最大タグ数
  public static final int MAX_SIZE = 50;

  private final Collection<Tag> tags;

  private TagCollection(Collection<Tag> tags) {
    this.tags = tags;
  }

  /**
   * タグのコレクションを作成する。
   *
   * <p>タグ名が重複するタグは先頭のものだけを残す。
   *
   * @param tags タグ一覧。（重複除去後に50個以内。）
   * @return タグのコレクション
   */
  public static TagCollection create(Collection<Tag> tags) {
    Set<String> namesSet = new HashSet<>();
    List<Tag> uniqueTag = tags.stream().filter(tag -> namesSet.add(tag.name().value())).toList();
    if (uniqueTag.size() > MAX_SIZE) {
      throw new InvalidValueException("タグは" + MAX_SIZE + "個以内で指定してください");
    }
    return new TagCollection(uniqueTag);
  }

  public Collection<Tag> values() {
    return Collections.unmodifiableCollection(tags);
  }

  public List<String> nameValues() {
    return tags.stream().map(tag -> tag.name().value()).toList();
  }
}
