meta-java
=========

Java support for OpenEmbedded/oe-core-based builds. `LAYERSERIES_COMPAT`
covers `styhead` and `wrynose`.

This layer depends on:

* `openembedded-core`
* `meta-openembedded` (`meta-oe`)

No other layer dependency -- in particular, nothing here requires Poky;
build against plain oe-core with a `nodistro`-style distro.

OpenJDK 8
---------

The actively maintained path through this layer is OpenJDK 8, fetched from
the Skara-converted `jdk8u` git mirrors rather than the old hg.openjdk.java.net
tarballs, patched forward for current oe-core (GCC 14, autoconf >= 2.72):

* `openjdk-8-native`/`openjdk-8-bootstrap-native` -- the native build,
  bootstrapped from a prebuilt Temurin JDK 8 snapshot rather than a
  from-scratch bootstrap chain
* `openjdk-8` (target) -- three variants selected automatically by
  `COMPATIBLE_HOST`/machine override:
  - `openjdk-8-release.inc`: the mainline `jdk8u` git mirror (generic
    target, x86/x86-64/etc.)
  - `openjdk-8-release-aarch64.inc`: the `shenandoah-jdk8u` aarch64 port
  - `openjdk-8-release-aarch32.inc`: the `aarch32-port-jdk8u` port, for
    `armv7a`/`armv7ve`
* `openjre-8` -- JRE-only package split of the same recipe

RISC-V is explicitly marked incompatible (`COMPATIBLE_HOST`): OpenJDK 8
predates RISC-V and no port exists in any jdk8u mirror, so recipes skip
cleanly there instead of failing in `do_compile`.

OpenJDK 7 (`openjdk-7`, via the old IcedTea7 build) is still carried for
compatibility but is not part of the actively maintained path.

Legacy Java toolchain
----------------------

The rest of the layer is the classic from-source OpenEmbedded Java stack,
predating the OpenJDK 8 work above and not actively exercised by it:
`cacao`/`jamvm` (initial and full VMs), GNU Classpath, `ecj`
(Eclipse Compiler for Java, used as the initial bootstrap compiler),
`fastjar`, Classpathx (`gnujaf`/`gnumail`/`inetlib`), and various
XML/servlet support recipes (`xerces-j`, `xalan-j`, `jaxp`, `dom4j`,
`servlet-api`, ...). To use it instead of OpenJDK 8, set in a distro
include or `local.conf`:

```
# Possible provider: cacao-initial-native and jamvm-initial-native
PREFERRED_PROVIDER_virtual/java-initial-native = "cacao-initial-native"

# Possible provider: cacao-native and jamvm-native
PREFERRED_PROVIDER_virtual/java-native = "jamvm-native"

# Only one provider for now
PREFERRED_PROVIDER_virtual/javac-native = "ecj-bootstrap-native"
```

Usage
-----

Add this layer's path to `bblayers.conf` alongside `meta-openembedded/meta-oe`,
then depend on `openjdk-8-native`, `openjdk-8`/`openjre-8`, or the legacy
providers above as needed.

Contributing
------------

See `CONTRIBUTING.md`.
