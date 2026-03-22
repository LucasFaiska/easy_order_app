package com.sraccelerator.easyorder.domain.usecase

import com.sraccelerator.easyorder.data.CartRepository
import javax.inject.Inject

interface ClearCartUseCase {
    operator fun invoke()
}

internal class ClearCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository
) : ClearCartUseCase {
    override fun invoke() {
        repository.clear()
    }
}