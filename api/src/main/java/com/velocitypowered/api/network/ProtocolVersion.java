package com.velocitypowered.api.network;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents each Minecraft protocol version.
 */
public enum ProtocolVersion {
  UNKNOWN(-1, "Unknown"),
  LEGACY(-2, "Legacy"),
  MINECRAFT_1_8(47, "1.8"),
  MINECRAFT_1_9(107, "1.9"),
  MINECRAFT_1_9_1(108, "1.9.1"),
  MINECRAFT_1_9_2(109, "1.9.2"),
  MINECRAFT_1_9_4(110, "1.9.4"),
  MINECRAFT_1_10(210, "1.10"),
  MINECRAFT_1_11(315, "1.11"),
  MINECRAFT_1_11_1(316, "1.11.1"),
  MINECRAFT_1_12(335, "1.12"),
  MINECRAFT_1_12_1(338, "1.12.1"),
  MINECRAFT_1_12_2(340, "1.12.2"),
  MINECRAFT_1_13(393, "1.13"),
  MINECRAFT_1_13_1(401, "1.13.1"),
  MINECRAFT_1_13_2(404, "1.13.2"),
  MINECRAFT_1_14(477, "1.14"),
  MINECRAFT_1_14_1(480, "1.14.1"),
  MINECRAFT_1_14_2(485, "1.14.2"),
  MINECRAFT_1_14_3(490, "1.14.3"),
  MINECRAFT_1_14_COMBAT_1(500, "1_14_combat-212796", ProtocolFlags.COMBAT_TEST),
  MINECRAFT_1_14_4(498, "1.14.4"),
  MINECRAFT_1_14_COMBAT_2(501, "1.14_combat-0", ProtocolFlags.COMBAT_TEST),
  MINECRAFT_1_14_COMBAT_3(502, "1.14_combat-3", ProtocolFlags.COMBAT_TEST),
  MINECRAFT_1_15(573, "1.15"),
  MINECRAFT_1_15_COMBAT_4(600, "1.15_combat-0", ProtocolFlags.COMBAT_TEST),
  MINECRAFT_1_15_1(575, "1.15.1"),
  MINECRAFT_1_15_2(578, "1.15.2"),
  MINECRAFT_1_15_COMBAT_5(601, "1.15_combat-6", ProtocolFlags.COMBAT_TEST),
  MINECRAFT_1_16(735, "1.16"),
  MINECRAFT_1_16_1(736, "1.16.1"),
  MINECRAFT_1_16_2(751, "1.16.2"),
  MINECRAFT_1_16_COMBAT_6(801, "1.16_combat-0", ProtocolFlags.COMBAT_TEST),
  MINECRAFT_1_16_COMBAT_7(802, "1.16_combat-3", ProtocolFlags.COMBAT_TEST),
  MINECRAFT_1_16_COMBAT_8(803, "1.16_combat-5", ProtocolFlags.COMBAT_TEST),
  MINECRAFT_1_16_3(753, "1.16.3"),
  MINECRAFT_1_16_4(754, "1.16.4");

  private final int protocol;
  private final String name;
  private final ProtocolFlags flags;

  /**
   * Represents the lowest supported version.
   */
  public static final ProtocolVersion MINIMUM_VERSION = MINECRAFT_1_8;
  /**
   * Represents the highest supported version.
   */
  public static final ProtocolVersion MAXIMUM_VERSION = values()[values().length - 1];

  /**
   * The user-friendly representation of the lowest and highest supported versions.
   */
  public static final String SUPPORTED_VERSION_STRING = String
      .format("%s-%s", MINIMUM_VERSION, MAXIMUM_VERSION);

  /**
   * A map linking the protocol version number to its {@link ProtocolVersion} representation.
   */
  public static final ImmutableMap<Integer, ProtocolVersion> ID_TO_PROTOCOL_CONSTANT;

  static {
    Map<Integer, ProtocolVersion> versions = new HashMap<>();
    for (ProtocolVersion version : values()) {
      versions.put(version.protocol, version);
    }

    ID_TO_PROTOCOL_CONSTANT = ImmutableMap.copyOf(versions);
  }

  /**
   * A set containing all the protocols that the proxy actually supports, excluding special-purpose
   * "versions" like {@link #LEGACY} and {@link #UNKNOWN}.
   */
  public static final Set<ProtocolVersion> SUPPORTED_VERSIONS;

  static {
    Set<ProtocolVersion> versions = EnumSet.noneOf(ProtocolVersion.class);
    for (ProtocolVersion value : values()) {
      if (!value.isUnknown() && !value.isLegacy()) {
        versions.add(value);
      }
    }

    SUPPORTED_VERSIONS = Sets.immutableEnumSet(versions);
  }

  ProtocolVersion(int protocol, String name) {
    this.protocol = protocol;
    this.name = name;
    this.flags = ProtocolFlags.RELEASE;
  }

  ProtocolVersion(int protocol, String name, ProtocolFlags flags) {
    this.protocol = protocol;
    this.name = name;
    this.flags = flags;
  }

  /**
   * Returns the protocol as an int.
   *
   * @return the protocol version
   */
  public int getProtocol() {
    return protocol;
  }

  /**
   * Returns the user-friendly name for this protocol.
   *
   * @return the protocol name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the {@link ProtocolVersion} for the given protocol.
   *
   * @param protocol the protocol as an int
   * @return the protocol version
   */
  public static ProtocolVersion getProtocolVersion(int protocol) {
    return ID_TO_PROTOCOL_CONSTANT.getOrDefault(protocol, UNKNOWN);
  }

  /**
   * Returns whether the protocol is supported.
   *
   * @param protocol the protocol as an int
   * @return if the protocol supported
   */
  public static boolean isSupported(int protocol) {
    ProtocolVersion version = ID_TO_PROTOCOL_CONSTANT.get(protocol);

    return version != null && !version.isUnknown();
  }

  /**
   * Returns whether the {@link ProtocolVersion} is supported.
   *
   * @param version the protocol version
   * @return if the protocol supported
   */
  public static boolean isSupported(ProtocolVersion version) {
    return version != null && !version.isUnknown();
  }

  /**
   * Returns whether this {@link ProtocolVersion} is unknown to the proxy.
   *
   * @return if the protocol is unknown
   */
  public boolean isUnknown() {
    return this == UNKNOWN;
  }

  /**
   * Returns whether this {@link ProtocolVersion} is a legacy protocol.
   *
   * @return if the protocol is legacy
   */
  public boolean isLegacy() {
    return this == LEGACY;
  }

  @Override
  public String toString() {
    return name;
  }

  /**
   * Returns this {@link ProtocolVersion}'s {@link ProtocolFlags}.
   *
   * @return the {@link ProtocolFlags} for this {@link ProtocolVersion}
   */
  public ProtocolFlags getFlags() {
    return this.flags;
  }
}
