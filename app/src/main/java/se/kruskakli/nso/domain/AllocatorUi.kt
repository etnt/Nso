package se.kruskakli.nso.domain

data class AllocatorUi(
    val allocators: List<NsoAllocator>,
    val blocksSize: String,
    val carriersSize: String,
    val utilization: String,
    val mbcs: SizeInfoUi,
    val sbcs: SizeInfoUi
) {
    data class NsoAllocator(
        val name: String,
        val instance: Int,
        val blocksSize: String,
        val carriersSize: String,
        val utilization: String,
        val mbcs: SizeInfoUi,
        val sbcs: SizeInfoUi
    )
    data class SizeInfoUi(
        val blocksSize: String,
        val carriersSize: String,
        val utilization: String
    )
}
