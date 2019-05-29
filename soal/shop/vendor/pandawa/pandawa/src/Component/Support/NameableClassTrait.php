<?php
/**
 * This file is part of the Pandawa package.
 *
 * (c) 2018 Pandawa <https://github.com/bl4ckbon3/pandawa>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

declare(strict_types=1);

namespace Pandawa\Component\Support;

use Illuminate\Support\Str;
use ReflectionClass;
use ReflectionException;

/**
 * @author  Iqbal Maulana <iq.bluejack@gmail.com>
 */
trait NameableClassTrait
{
    /**
     * @return string
     * @throws ReflectionException
     */
    public static function name(): string
    {
        $class = new ReflectionClass(get_called_class());

        return Str::kebab($class->getShortName());
    }
}
