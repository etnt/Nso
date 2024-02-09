package se.kruskakli.nso.data.debug.inet

import se.kruskakli.nso.domain.InetUi


fun All.toInetUi(): InetUi {
    return InetUi(
        foreignAddress = foreignAddress,
        localAddress = localAddress,
        module = module,
        owner = owner,
        port = port,
        received = received,
        sent = sent,
        state = state,
        type = type
    )
}