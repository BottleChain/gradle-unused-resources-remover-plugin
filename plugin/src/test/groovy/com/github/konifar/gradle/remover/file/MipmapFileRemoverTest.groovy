package com.github.konifar.gradle.remover.file

import spock.lang.Specification

class MipmapFileRemoverTest extends Specification {

    def remover = new MipmapFileRemover()

    def "type is mipmap"() {
        expect:
        remover.type == "mipmap"
    }

    def "pattern matches"() {
        def pattern = remover.createPattern("ic_launcher")
        def isMatched = false
        if (fileText =~ pattern) {
            isMatched = true
        }

        expect:
        isMatched == expected

        where:
        fileText                      | expected
        "R.mipmap.ic_launcher"        | true
        "@mipmap/ic_launcher\""       | true
        "R.drawable.ic_launch"        | false
        "@mipmap/ic_launcher_round\"" | false
        // "R.mipmap.ic_launcher_round"   | false
    }
}