package com.everrefine.elms.domain.model.tag;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** タグのコレクション */
public class TagCollection {

  private final Collection<Tag> tags;

  private TagCollection(Collection<Tag> tags) {
    this.tags = tags;
  }

  public static TagCollection create(Collection<Tag> tags) {
    Set<String> namesSet = new HashSet<>();
    List<Tag> uniqueTag = tags.stream().filter(tag -> namesSet.add(tag.name().value())).toList();
    return new TagCollection(uniqueTag);
  }

  public Collection<Tag> values() {
    return Collections.unmodifiableCollection(tags);
  }

  public List<String> nameValues() {
    return tags.stream().map(tag -> tag.name().value()).toList();
  }
}
