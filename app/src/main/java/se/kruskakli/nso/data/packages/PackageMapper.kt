package se.kruskakli.nso.data.packages

import se.kruskakli.nsopackages.domain.PackageUi

fun Package.toPackageUi(): PackageUi {
    return PackageUi(
        name = name,
        packageVersion = packageVersion,
        description = description,
        directory = directory,
        ncsMinVersion = ncsMinVersion
    )
}