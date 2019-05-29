<?php
/**
 * Created by IntelliJ IDEA.
 * User: komalasurya
 * Date: 18/12/18
 * Time: 09.21
 */

namespace Shop\User\Command;


use Pandawa\Component\Message\AbstractCommand;
use Pandawa\Component\Message\NameableMessageInterface;
use Pandawa\Component\Support\NameableClassTrait;

final class RegisterUser extends AbstractCommand implements NameableMessageInterface
{
    use NameableClassTrait;

    /**
     * @var string
     */
    private $username;

    /**
     * @var string
     */
    private $name;
    /**
     * @var string
     */
    private $password;
    /**
     * @var ?string
     */
    private $email;
    /**
     * @var ?string
     */
    private $phone;
    /**
     * @var ?string
     */
    private $gender;

    /**
     * @return mixed
     */
    public function getGender()
    {
        return $this->gender;
    }

    /**
     * @return mixed
     */
    public function getPhone()
    {
        return $this->phone;
    }

    /**
     * @return mixed
     */
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * @return string
     */
    public function getPassword(): string
    {
        return $this->password;
    }

    /**
     * @return string
     */
    public function getName(): string
    {
        return $this->name;
    }

    /**
     * @return string
     */
    public function getUsername(): string
    {
        return $this->username;
    }
}