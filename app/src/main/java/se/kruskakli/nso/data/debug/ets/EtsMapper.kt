package se.kruskakli.nso.data.debug.ets

import se.kruskakli.nso.domain.EtsUi

fun All.toEtsUi(): EtsUi {
    return EtsUi(
        id = id,
        mem = mem,
        name = name,
        owner = owner,
        size = size,
        type = type
    )
}
