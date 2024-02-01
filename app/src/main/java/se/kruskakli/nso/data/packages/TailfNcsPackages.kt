package se.kruskakli.nso.data.packages

import com.squareup.moshi.Json

/*
{
  "tailf-ncs:packages": {
    "package": [
      {
        "name": "gned",
        "package-version": "1.0",
        "description": "Ned state test",
        "ncs-min-version": ["2.0"],
        "directory": "./state/packages-in-use/1/gned",
        "component": [
          {
            "name": "xned",
            "ned": {
              "generic": {
                "ned-id": "gned-id:gned-id",
                "java-class-name": "com.example.gned.GNed"
              },
              "device": {
                "vendor": "Nedder"
              }
            }
          }
        ],
        "oper-status": {
          "up": [null]
        }
      },
      {
        "name": "gserv",
        "package-version": "1.0",
        "description": "Skeleton for a template-based service",
        "ncs-min-version": ["3.0"],
        "directory": "./state/packages-in-use/1/gserv",
        "templates": ["gserv"],
        "template-loading-mode": "strict",
        "oper-status": {
          "up": [null]
        }
      }
    ]
  }
}

 */

data class TailfNcsPackages(
    @Json(name = "package") val nsoPackages: List<Package>
)