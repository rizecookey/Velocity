package com.velocitypowered.api.network;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;

@Immutable
public class ProtocolFlags {
  public static final ProtocolFlags EMPTY = new ProtocolFlags();
  public static final ProtocolFlags RELEASE = new ProtocolFlags(Flag.RELEASE);
  public static final ProtocolFlags COMBAT_TEST = new ProtocolFlags(
      Flag.PRE_RELEASE,
      Flag.COMBAT_TEST
  );

  private final ImmutableList<Flag> flags;

  public ProtocolFlags(Flag... flags) {
    this.flags = ImmutableList.copyOf(flags);
  }

  public boolean has(Flag flag) {
    return flags.contains(flag);
  }

  public enum Flag {
    RELEASE, SNAPSHOT, PRE_RELEASE, COMBAT_TEST
  }
}
