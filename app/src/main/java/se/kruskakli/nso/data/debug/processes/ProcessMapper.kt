package se.kruskakli.nso.data.debug.processes

import se.kruskakli.nso.domain.ProcessUi

fun NsoProcess.toProcessUi(): ProcessUi {
    return ProcessUi(
        ccall = ccall,
        icall = icall,
        memory = memory,
        msgs = msgs,
        name = name,
        pid = pid,
        reds = reds,
        sharedBinaries = sharedBinaries
    )
}